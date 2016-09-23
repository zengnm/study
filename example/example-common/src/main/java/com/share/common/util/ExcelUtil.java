package com.share.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Excel读写工具类
 */
public class ExcelUtil {
    private final static Logger LOG = Logger.getLogger(ExcelUtil.class);
    private final static String TEMPLATE_PATH = "/templates/";  //导出的模板文件位置
    private final static String ENCODING = "GBK"; //编码

    /**
     * 读取Excel文件数据（Excel2007, 后缀.xlsx）
     *
     * @param file Excel2007文件的文件对象
     * @return list 文件第二行开始后的所有行，每行为一个String[]
     * @throws Exception 模板文件不正确
     */
    public static List<String[]> readExcel2007(File file) {
        List<String[]> list = new ArrayList<String[]>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ZipFile xlsxFile = null;
        String sharedStrings[] = null;
        try {
            xlsxFile = new ZipFile(file);
            // 先读取sharedStrings.xml这个文件,字符串共享池
            ZipEntry sharedStringXML = xlsxFile.getEntry("xl/sharedStrings.xml");
            if (sharedStringXML != null) {
                InputStream sharedStringXMLIS = xlsxFile.getInputStream(sharedStringXML);
                Document sharedString = dbf.newDocumentBuilder().parse(sharedStringXMLIS);
                //结点si下有t结点以及其它合并单元格的结点
                NodeList str = sharedString.getElementsByTagName("si");
                sharedStrings = new String[str.getLength()];
                for (int n = 0; n < str.getLength(); n++) {
                    Node nsi = str.item(n);
                    NodeList ndt = nsi.getChildNodes();
                    StringBuilder temp = new StringBuilder();
                    //处理合并的
                    for (int m = 0; m < ndt.getLength(); m++) {
                        temp.append(ndt.item(m).getTextContent());
                    }
                    sharedStrings[n] = temp.toString();
                }
            }
            // 找到解压文件夹里的workbook.xml,此文件中包含了这张工作表中有几个sheet
            ZipEntry workbookXML = xlsxFile.getEntry("xl/workbook.xml");
            InputStream workbookXMLIS = xlsxFile.getInputStream(workbookXML);

            Document doc = dbf.newDocumentBuilder().parse(workbookXMLIS);
            // 获取一共有几个sheet
            NodeList nl = doc.getElementsByTagName("sheet");
            for (int i = 0; i < nl.getLength(); i++) {
                Element element = (Element) nl.item(i);// 将node转化为element，用来得到每个节点的属性
                // 接着就要到解压文件夹里找到对应的name值的xml文件，比如在workbook.xml中有<sheet name="Sheet1" sheetId="1" r:id="rId1" /> 节点
                // 那么就可以在解压文件夹里的xl/worksheets下找到sheet1.xml,这个xml文件夹里就是包含的表格的内容
                ZipEntry sheetXML = xlsxFile.getEntry("xl/worksheets/sheet" + element.getAttribute("sheetId")
                        .toLowerCase() + ".xml");
                InputStream sheetXMLIS = xlsxFile.getInputStream(sheetXML);
                Document sheetdoc = dbf.newDocumentBuilder().parse(sheetXMLIS);
                NodeList rowdata = sheetdoc.getElementsByTagName("row");
                for (int j = 1; j < rowdata.getLength(); j++) {
                    // 得到每个行 行的格式：
                    Element row = (Element) rowdata.item(j);
                    // 根据行得到每个行中的列
                    NodeList columndata = row.getElementsByTagName("c");
                    String[] temp = new String[columndata.getLength()];
                    for (int k = 0; k < columndata.getLength(); k++) {
                        Element column = (Element) columndata.item(k);
                        NodeList values = column.getElementsByTagName("v");
                        Element value = (Element) values.item(0);
                        if (column.getAttribute("t") != null && column.getAttribute("t").equals("s")) {
                            // 如果是共享字符串则在sharedstring.xml里查找该列的值
                            temp[k] = sharedStrings[Integer.parseInt(value.getTextContent())];
                        } else {
                            if (null != value) {
                                temp[k] = value.getTextContent();
                            }
                        }
                    }
                    list.add(temp);
                }
            }
        } catch (Exception e) {
            LOG.error("ExcelUtil readExcel error!", e);
        } finally {
            if (null != xlsxFile) {
                try {
                    xlsxFile.close();
                } catch (Exception e) {
                    LOG.error("ExcelUtil readExcel error!", e);
                }
            }
        }
        return list;
    }

    /**
     * 向客户端导出Excel文件
     *
     * @param request
     * @param response
     * @param exportName   导出文件的文件名
     * @param templateName 模板文件名(位于指定的路径下)
     * @param dataMap      需要导出的数据
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String exportName,
                                   String templateName, Map<String, Object> dataMap) {
        response.reset();  //清空输出流
        response.setCharacterEncoding(ENCODING);//设置编码
        response.setContentType("application/vnd.ms-excel");

        XLSTransformer transformer = new XLSTransformer();
        InputStream is = null;
        OutputStream os = null;
        try {
            response.addHeader("Content-Disposition", "attachment;filename=\"" + new String(exportName.getBytes
                    ("GBK"), "iso-8859-1") + "\"");
            is = request.getSession().getServletContext().getResourceAsStream(TEMPLATE_PATH + templateName);
            HSSFWorkbook workbook = transformer.transformXLS(is, dataMap);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            LOG.error("ExcelUtil exportExcel error!", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOG.error("ExcelUtil -> exportExcel() -> is.close() error!", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.error("ExcelUtil -> exportExcel() -> os.close() error!", e);
                }
            }
        }
    }

    /**
     * 获取模板文件的全路径名
     *
     * @param request
     * @param templateName 模板文件名(位于指定的路径下)
     * @return
     */
    public static String getTemplateFullPath(HttpServletRequest request, String templateName) {
        return request.getSession().getServletContext().getRealPath("/") + TEMPLATE_PATH + templateName;
    }

    /**
     * 向客户端导出Excel文件
     *
     * @param response
     * @param exportName：导出文件的文件名
     * @param dataMap：需要导出的数据
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String exportName, String templateFullPath,
                                   Map<String, Object> dataMap) {
        response.reset();  //清空输出流
        response.setCharacterEncoding(ENCODING);//设置编码
        response.setContentType("application/vnd.ms-excel");

        XLSTransformer transformer = new XLSTransformer();
        InputStream is = null;
        OutputStream os = null;
        try {
            response.addHeader("Content-Disposition", "attachment;filename=\"" + new String(exportName.getBytes
                    ("GBK"), "iso-8859-1") + "\"");
            is = new BufferedInputStream(new FileInputStream(templateFullPath));
            HSSFWorkbook workbook = transformer.transformXLS(is, dataMap);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            LOG.error("ExcelUtil exportExcel error!", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOG.error("ExcelUtil -> exportExcel() -> is.close() error!", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.error("ExcelUtil -> exportExcel() -> os.close() error!", e);
                }
            }
        }
    }

    /**
     * 向客户端导出Excel文件
     *
     * @param response
     * @param path
     */
    // todo
    public static void exportExcel(HttpServletResponse response, String path) {
        response.reset(); // 清空response
        response.setContentType("application/vnd.ms-excel;charset=gb2312");

        InputStream is = null;
        OutputStream os = null;
        try {
            File file = new File(path);
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes()));
            response.addHeader("Content-Length", "" + file.length());

            // 以流的形式下载文件。
            is = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);

            os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
        } catch (IOException e) {
            LOG.error("ExcelUtil exportExcel error!", e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOG.error("ExcelUtil exportExcel is close error!", e);
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.error("ExcelUtil exportExcel os close error!", e);
                }
            }
        }
    }

    /**
     * 创建EXCEL
     *
     * @param exportPath   导出的EXCLE存放路径及文件名
     * @param templatePath 模板文件路径及文件名
     * @param dataMap
     */
    public boolean createExcel(String exportPath, String templatePath, Map<String, Object> dataMap) {
        XLSTransformer transformer = new XLSTransformer();
        try {
            long start = System.currentTimeMillis();
            transformer.transformXLS(templatePath, dataMap, exportPath);
            LOG.info("createExcel 耗时：" + (System.currentTimeMillis() - start));
            return true;
        } catch (ParsePropertyException e) {
            LOG.error("createExcel error : ", e);
        } catch (IOException e) {
            LOG.error("createExcel error : ", e);
        }

        return false;
    }

    /**
     * 创建指定编码的文件
     *
     * @param exportPath
     * @param title
     * @param dataList
     * @param encode     编码格式
     * @return
     */
    public boolean createFile(String exportPath, String title, List<String> dataList, String encode) {
        if (StringUtils.isBlank(exportPath) || dataList == null || dataList.isEmpty()
                || StringUtils.isBlank(encode)) {
            LOG.error("导出文件路径为空||导出数据dataList 为空||编码encode为空");
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(exportPath, true);
            return writeData(fos, encode, title, dataList);
        } catch (FileNotFoundException e) {
            LOG.error("创建导出文件失败.", e);
            return false;
        }
    }

    /**
     * 创建指定编码的文件及表头
     *
     * @param exportPath 全路径加名字及类型
     * @param title      不追加写入title，直接替换原来的文件
     * @param encode     编码格式
     * @return
     */
    public boolean createFileTitle(String exportPath, String title, String encode) {
        if (StringUtils.isBlank(exportPath) || StringUtils.isBlank(title) || StringUtils.isBlank(encode)) {
            LOG.info("导出文件路径为空|| title为空 || 编码encode为空");
            return false;
        }
        LOG.info("exportPath : " + exportPath);
        FileOutputStream fos = null;
        try {
            /*false 不追加写入title，直接替换原来的文件*/
            fos = new FileOutputStream(exportPath, false);
            return writeData(fos, encode, title, new ArrayList<String>());
        } catch (FileNotFoundException e) {
            LOG.error("创建油卡对账文件标题失败.", e);
            return false;
        }
    }

    /**
     * 写数据
     *
     * @param os       输出流
     * @param encode   编码
     * @param title    标题
     * @param dataList 数据
     * @return true 成功
     */
    private boolean writeData(OutputStream os, String encode, String title, List<String> dataList) {
        long start = System.currentTimeMillis();
        BufferedWriter bw = null;
        try {
            OutputStreamWriter oswriter = new OutputStreamWriter(os, encode);
            bw = new BufferedWriter(oswriter);
            if (StringUtils.isNotBlank(title)) {
                // 添加标题头
                bw.write(title);
                bw.newLine();
            }
            for (String data : dataList) {
                bw.write(data);
                bw.newLine();
            }
            bw.flush();
            os.flush();
            LOG.info(dataList.size() + " 条数据写入文件, 耗时(ms)：" + (System.currentTimeMillis() - start));
            return true;
        } catch (IOException e) {
            LOG.error("写入文件失败,文件路径：", e);
            return false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.error("关闭输出流失败", e);
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    LOG.error("关闭输出流失败", e);
                }
            }
        }
    }


}

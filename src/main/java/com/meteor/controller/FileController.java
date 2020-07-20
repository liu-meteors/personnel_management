package com.meteor.controller;

import com.meteor.pojo.Contract;
import com.meteor.pojo.ContractHistory;
import com.meteor.pojo.Interview;
import com.meteor.service.ContractHistoryService;
import com.meteor.service.ContractService;
import com.meteor.service.InterviewService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/5 10:39
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
public class FileController {
    @Autowired
    InterviewService interviewService;
    @Autowired
    ContractService contractService;

    @Autowired
    ContractHistoryService contractHistoryService;


    private final static String fileDir = "files";
    private final static String rootPath = System.getProperty("user.home") + File.separator + fileDir + File.separator;
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    /**
     * @Description: 上传简历
     * @Param: * @Param: file
     * @Param: req
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @PostMapping("/import")
    public Map<String, String> importData(MultipartFile file, HttpServletRequest req) throws IOException {
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/upload") + "/interview";
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + "/interview/" + newName;
        System.out.println(url);
        System.out.println(realPath + "/" + newName);
        String fileAddress = realPath + "/" + newName;
        Map<String, String> map = new HashMap<>();
        map.put("fileAddress", fileAddress);
        map.put("fileUrl", url);
        return map;
    }

    /**
     * @Description: 下载简历
     * @Param: * @Param: fileName
     * @Param: id
     * @Param: response
     * @Param: request
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/download/{id}")
    public Object downloadFile(@RequestParam String fileName, @PathVariable("id") Integer id, final HttpServletResponse response, final HttpServletRequest request) {
        System.out.println("路径：：：" + fileName);
        Interview interview = interviewService.getInterviewById(id);
        String path = interview.getFileAddress();
        OutputStream os = null;
        InputStream is = null;
        try {

            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            //读取流
//            String path=request.getServletContext().getRealPath("//upload") + "\\interview";
            System.err.println(path);
            File f = new File(path);
            System.out.println(f.length());
            is = new FileInputStream(f);


            if (is == null) {

                return "下载失败，查看文件是否复存在";
            }

            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return "下载失败";
        }
        //文件的关闭放在finally中
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    /**
     * @Description: 上传合同模板
     * @Param: * @Param: file
     * @Param: req
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @PostMapping("/importContract")
    public Map<String, String> importContract(MultipartFile file, HttpServletRequest req) throws IOException {
        System.out.println("上传合同");
        String realPath = req.getServletContext().getRealPath("/upload") + "/contract";
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + "/contract/" + newName;
        System.out.println(url);
        System.out.println(realPath + "/" + newName);
        String fileAddress = realPath + "/" + newName;
        Map<String, String> map = new HashMap<>();
        map.put("fileAddress", fileAddress);
        map.put("fileUrl", url);
        return map;
    }

    /**
     * @Description: 下载合同模板
     * @Param: * @Param: fileName
     * @Param: id
     * @Param: response
     * @Param: request
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/downloadContract/{id}")
    public Object downloadContract(@RequestParam String fileName, @PathVariable("id") Integer id, final HttpServletResponse response, final HttpServletRequest request) {
        Contract contract = contractService.getContractById(id);
        String path = contract.getFileAddress();
        OutputStream os = null;
        InputStream is = null;
        try {

            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            //读取流
//            String path=request.getServletContext().getRealPath("//upload") + "\\interview";
            System.err.println(path);
            File f = new File(path);
            System.out.println(f.length());
            is = new FileInputStream(f);


            if (is == null) {

                return "下载失败，查看文件是否复存在";
            }

            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return "下载失败";
        }
        //文件的关闭放在finally中
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }


    /**
     * @Description: 上传邮件附件
     * @Param: * @Param: file
     * @Param: req
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @PostMapping("/importMail")
    public Map<String, String> importMail(MultipartFile file, HttpServletRequest req) throws IOException {
        String realPath = req.getServletContext().getRealPath("/upload") + "/mail";
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + "/interview/" + newName;
        System.out.println(url);
        System.out.println(realPath + "/" + newName);
        String fileAddress = realPath + "/" + newName;
        Map<String, String> map = new HashMap<>();
        map.put("fileAddress", fileAddress);
        map.put("fileName", newName);
        return map;
    }


    @PostMapping("/importHistory")
    public Map<String, String> importHistory(MultipartFile file, HttpServletRequest req) throws IOException {
        System.out.println("上传员工合同");
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/upload") + "/history";
        System.out.println(realPath);
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + "/history/" + newName;
        System.out.println(url);
        System.out.println(realPath + "/" + newName);
        String fileAddress = realPath + "/" + newName;
        Map<String, String> map = new HashMap<>();
        map.put("fileAddress", fileAddress);
        map.put("fileUrl", url);
        return map;
    }

    /**
     * @Description:下载合同历史文件
     * @Param: * @Param: fileName
     * @Param: id
     * @Param: response
     * @Param: request
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/downloadHistory/{id}")
    public Object downloadHistory(@RequestParam String fileName, @PathVariable("id") Integer id, final HttpServletResponse response, final HttpServletRequest request) {
        System.out.println("路径：：：" + fileName);
        //根据id查询合同历史
        ContractHistory contractHistory = contractHistoryService.getHistoryById(id);
        // 获取路径
        String path = contractHistory.getFileAddress();
        OutputStream os = null;
        InputStream is = null;
        try {

            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            //读取流
//            String path=request.getServletContext().getRealPath("//upload") + "\\interview";
            System.err.println(path);
            File f = new File(path);
            System.out.println(f.length());
            is = new FileInputStream(f);


            if (is == null) {

                return "下载失败，查看文件是否复存在";
            }

            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return "下载失败";
        }
        //文件的关闭放在finally中
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
}

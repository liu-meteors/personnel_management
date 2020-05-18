package com.meteor.service.impl;

import com.meteor.mapper.InterviewMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Department;
import com.meteor.pojo.Interview;
import com.meteor.pojo.Position;
import com.meteor.service.DepartmentService;
import com.meteor.service.InterviewService;
import com.meteor.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/6 16:03
 * @description：面试处理类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    InterviewMapper interviewMapper;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionMapper positionMapper;

    public static final String DEFAULT_ENCODING = "UTF-8";
    @Value("${spring.mail.username}")
    private String userName;
    @Autowired
    private JavaMailSender mailSender;

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public int addInterview(Interview interview) {
        interview.setIsSend(0);
        interview.setIsView(0);
        interview.setViewDate(DateUtils.dealDateFormat(interview.getViewDateStr()));
        System.out.println(interview);
        return interviewMapper.addInterview(interview);
    }

    @Override
    public int deleteInterviewById(Integer id) {
        return interviewMapper.deleteInterviewById(id);
    }

    @Override
    public int updateInterviewById(Interview interview) {
        return interviewMapper.updateInterviewById(interview);
    }

    @Override
    public List<Interview> getAllInterview() {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        List<Interview> interviews=interviewMapper.getAllInterview();
        for (int i=0;i<interviews.size();i++){
            interviews.set(i,setInterViewInformation(interviews.get(i),departments,positions));
        }
        return interviews;
    }
    /**
     * @Description: 获取当天面试信息
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Interview> getAllInterviewNow() {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        List<Interview> interviews=interviewMapper.getAllInterviewNow();
        for (int i=0;i<interviews.size();i++){
            interviews.set(i,setInterViewInformation(interviews.get(i),departments,positions));
        }
        return interviews;
    }

    @Override
    public Interview getInterviewById(Integer id) {
        Interview interview=interviewMapper.getInterviewById(id);
        return interview;
    }


    public Interview setInterViewInformation(Interview interview, List<Department> departments, List<Position> positions){
        for (int i=0;i<departments.size();i++){
            if (departments.get(i).getId()==interview.getDepartment()){
                interview.setDepartmentName(departments.get(i).getName());
                break;
            }
        }
        for (int i=0;i<positions.size();i++){
            if (positions.get(i).getId()==interview.getPosite()){
                interview.setPositionName(positions.get(i).getName());
            }
        }
        if (interview.getIsView()==0){
            interview.setIsInterview("未面试");
        }else if (interview.getIsView()==1){
            if (interview.getIsSend()==0){
                interview.setIsInterview("已面试");
            }else {
                interview.setIsInterview("已发送");
            }
        }else {
            interview.setIsInterview("已拒绝");

        }
        interview.setViewDateStr(simpleDateFormat.format(interview.getViewDate()));
        return interview;

    }


    /**
     * 发送一个简单的文本邮件，可以附带附件：文本邮件发送的基本方法
     * @param subject ：邮件主题，即邮件的邮件名称
     * @param content ：邮件内容
     * @param toWho ：需要发送的人
     * @param ccPeoples ：需要抄送的人
     * @param bccPeoples ：需要密送的人
     * @param attachments ：需要附带的附件，附件请保证一定要存在，否则将会被忽略掉
     * @return
     */
    @Override
    public int sendSimpleTextMailActual(String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, String[] attachments){

        //检验参数：邮件主题、收件人、邮件内容必须不为空才能够保证基本的逻辑执行
        if(subject == null||toWho == null||toWho.length == 0||content == null){


            throw new RuntimeException("模板邮件无法继续发送，因为缺少必要的参数！");
        }


        //附件处理，需要处理附件时，需要使用二进制信息，使用 MimeMessage 类来进行处理
        if(attachments != null&&attachments.length > 0){

            try{
                //附件处理需要进行二进制传输
                MimeMessage mimeMessage = mailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,DEFAULT_ENCODING);

                //设置邮件的基本信息：这些函数都会在后面列出来
                boolean continueProcess = handleBasicInfo(helper,subject,content,toWho,ccPeoples,bccPeoples,false);

                //如果处理基本信息出现错误
                if(!continueProcess){


                    return 0;
                }

                //处理附件
                handleAttachment(helper,subject,attachments);

                //发送该邮件
                mailSender.send(mimeMessage);

                return 1;
            }catch(MessagingException e){
                return 0;
//                e.printStackTrace();

            }
        }else{

            //创建一个简单邮件信息对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //设置邮件的基本信息
            handleBasicInfo(simpleMailMessage,subject,content,toWho,ccPeoples,bccPeoples);

            //发送邮件
            mailSender.send(simpleMailMessage);
            return 1;
        }
    }

    /**
     * @param dep
     * @Description: 获取该部门当天的面试情况
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Interview> getAllInterviewNowByDep(Integer dep) {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        List<Interview> interviews=interviewMapper.getAllInterviewNowByDep(dep);
        for (int i=0;i<interviews.size();i++){
            interviews.set(i,setInterViewInformation(interviews.get(i),departments,positions));
        }
        return interviews;
    }

    /**
     * @param dep
     * @Description: 获取该部门当所有的面试情况
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Interview> getAllInterviewByDep(Integer dep) {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        List<Interview> interviews=interviewMapper.getAllInterviewByDep(dep);
        for (int i=0;i<interviews.size();i++){
            interviews.set(i,setInterViewInformation(interviews.get(i),departments,positions));
        }
        return interviews;
    }

    /**
     * 处理二进制邮件的基本信息，比如需要带附件的文本邮件、HTML文件、图片邮件、模板邮件等等
     *
     * @param mimeMessageHelper：二进制文件的包装类
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param ccPeoples：抄送人
     * @param bccPeoples：暗送人
     * @param isHtml：是否是HTML文件，用于区分带附件的简单文本邮件和真正的HTML文件
     *
     * @return ：返回这个过程中是否出现异常，当出现异常时会取消邮件的发送
     */
    private boolean handleBasicInfo(MimeMessageHelper mimeMessageHelper,String subject,String content,String[] toWho,String[] ccPeoples,String[] bccPeoples,boolean isHtml){

        try{
            //设置必要的邮件元素

            //设置发件人
            mimeMessageHelper.setFrom(userName);
            //设置邮件的主题
            mimeMessageHelper.setSubject(subject);
            //设置邮件的内容，区别是否是HTML邮件
            mimeMessageHelper.setText(content,isHtml);
            //设置邮件的收件人
            mimeMessageHelper.setTo(toWho);

            //设置非必要的邮件元素，在使用helper进行封装时，这些数据都不能够为空

            if(ccPeoples != null)
                //设置邮件的抄送人：MimeMessageHelper # Assert.notNull(cc, "Cc address array must not be null");
                mimeMessageHelper.setCc(ccPeoples);

            if(bccPeoples != null)
                //设置邮件的密送人：MimeMessageHelper # Assert.notNull(bcc, "Bcc address array must not be null");
                mimeMessageHelper.setBcc(bccPeoples);

            return true;
        }catch(MessagingException e){
            e.printStackTrace();

        }


        return false;
    }

    /**
     * 用于填充简单文本邮件的基本信息
     *
     * @param simpleMailMessage：文本邮件信息对象
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param ccPeoples：抄送人
     * @param bccPeoples：暗送人
     */
    private void handleBasicInfo(SimpleMailMessage simpleMailMessage,String subject,String content,String[] toWho,String[] ccPeoples,String[] bccPeoples){

        //设置发件人
        simpleMailMessage.setFrom(userName);
        //设置邮件的主题
        simpleMailMessage.setSubject(subject);
        //设置邮件的内容
        simpleMailMessage.setText(content);
        //设置邮件的收件人
        simpleMailMessage.setTo(toWho);
        //设置邮件的抄送人
        simpleMailMessage.setCc(ccPeoples);
        //设置邮件的密送人
        simpleMailMessage.setBcc(bccPeoples);
    }

    /**
     * 用于处理附件信息，附件需要 MimeMessage 对象
     *
     * @param mimeMessageHelper：处理附件的信息对象
     * @param subject：邮件的主题，用于日志记录
     * @param attachmentFilePaths：附件文件的路径，该路径要求可以定位到本机的一个资源
     */
    private void handleAttachment(MimeMessageHelper mimeMessageHelper, String subject, String[] attachmentFilePaths){

        //判断是否需要处理邮件的附件
        if(attachmentFilePaths != null&&attachmentFilePaths.length > 0){

            FileSystemResource resource;

            String fileName;

            //循环处理邮件的附件
            for(String attachmentFilePath : attachmentFilePaths){

                //获取该路径所对应的文件资源对象
                resource = new FileSystemResource(new File(attachmentFilePath));

                //判断该资源是否存在，当不存在时仅仅会打印一条警告日志，不会中断处理程序。
                // 也就是说在附件出现异常的情况下，邮件是可以正常发送的，所以请确定你发送的邮件附件在本机存在
                if(!resource.exists()){


                    //开启下一个资源的处理
                    continue;
                }

                //获取资源的名称
                fileName = resource.getFilename();

                try{

                    //添加附件
                    mimeMessageHelper.addAttachment(fileName,resource);

                }catch(MessagingException e){

                    e.printStackTrace();

                }
            }
        }
    }

}

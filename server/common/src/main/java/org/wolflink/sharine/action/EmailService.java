package org.wolflink.sharine.action;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.ErrorException;

@Service
public class EmailService {

    /**
     * 邮箱号
     */
    @Value("${spring.mail.username}")
    private String email;

    @Resource
    private RedisService redisService;
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送验证码
     *
     * @param username 收件人
     * @param code     验证码
     * @param minuter  过期时间 / 分钟
     */
    public void sendCode(String username, String code, Long minuter) throws MessagingException {

        String subject = "验证码";
        String content = "您的验证码为 " + code + " 有效期" + minuter + "分钟，请不要告诉他人哦！";

        // 构建一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        // 设置邮件主题
        message.setSubject(subject);
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
        // 设置邮件发送者
        messageHelper.setFrom(email);
        // 设置邮件接收者
        messageHelper.setTo(username);
        // 设置邮件正文
        messageHelper.setText(content, true); //true 代表发送html格式
        // 发送邮件
        javaMailSender.send(message);
    }
    public void mailVerify(String ipAddress,String mail,String verificationCode) {
        boolean checkResult = StringUtils.checkEmail(mail);
        if (!checkResult) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }
        String code = (String) redisService.get("email-cache:"+ipAddress);
        // 验证码错误
        if (code == null || !code.equals(verificationCode)) {
            throw new ErrorException(StatusCodeEnum.FAILED_PRECONDITION);
        }
    }
}

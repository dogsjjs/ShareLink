package cn.dogsjjs.share.service;

import cn.dogsjjs.share.entity.Email;
import cn.dogsjjs.share.util.ResultEntity;

public interface EmailService {

    ResultEntity<String> commonEmail(Email email);

    ResultEntity<String> commonEmail(String email);

}

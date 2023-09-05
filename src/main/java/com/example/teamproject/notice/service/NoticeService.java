package com.example.teamproject.notice.service;



import com.example.teamproject.notice.controller.form.RequestNoticeForm;
import com.example.teamproject.notice.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> list();
    Notice register(Notice notice);
    Notice read(Long noticeId);
    Notice modify(Long noticeId, RequestNoticeForm requestNOticeForm);
    void delete(Long noticeId);

}

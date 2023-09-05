package com.example.teamproject.notice.controller;



import com.example.teamproject.notice.controller.form.RequestNoticeForm;
import com.example.teamproject.notice.entity.Notice;
import com.example.teamproject.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    final private NoticeService noticeService;

    @GetMapping("/list")
    public List<Notice> noticeList() {
        log.info("noticeList()");

        List<Notice> returnedNoticeList = noticeService.list();
        log.info("returnedNoticeList: " + returnedNoticeList);

        final UUID uniqueId = UUID.randomUUID();
        log.info("uniqueId: " + uniqueId);

        return returnedNoticeList;
    }

}

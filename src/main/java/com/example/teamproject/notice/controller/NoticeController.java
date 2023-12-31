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
    @PostMapping("/register")
    public Notice registerNotice (@RequestBody RequestNoticeForm requestNoticeForm) {
        log.info("registerNotice()");

        return noticeService.register(requestNoticeForm.toNotice());
    }
    @GetMapping("/{noticeId}")
    public Notice readNotice (@PathVariable("noticeId") Long noticeId) {
        log.info("readNotice()");

        return noticeService.read(noticeId);
    }
    @PutMapping("/{noticeId}")
    public Notice modifyNotice (@PathVariable("noticeId") Long noticeId,
                                @RequestBody RequestNoticeForm requestNoticeForm) {
        log.info("modifyNotice(): " + requestNoticeForm + ", id: " + noticeId);

        return noticeService.modify(noticeId, requestNoticeForm);
    }
    @DeleteMapping("/{noticeId}")
    public void deleteNotice (@PathVariable("noticeId") Long noticeId) {
        log.info("deleteNotice()");

        noticeService.delete(noticeId);
    }

}

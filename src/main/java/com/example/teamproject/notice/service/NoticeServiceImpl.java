package com.example.teamproject.notice.service;



import com.example.teamproject.notice.controller.form.RequestNoticeForm;
import com.example.teamproject.notice.entity.Notice;
import com.example.teamproject.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    final private NoticeRepository noticeRepository;

    @Override
    public List<Notice> list() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeId"));
    }


    @Override
    public Notice register(Notice notice) {
        return noticeRepository.save(notice);
    }


}

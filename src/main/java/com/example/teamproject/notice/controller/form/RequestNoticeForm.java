package com.example.teamproject.notice.controller.form;

import com.example.teamproject.notice.entity.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestNoticeForm {
    final private String title;
    final private String content;
    final private String writer;

    public Notice toNotice() {
        return new Notice(title, writer, content);
    }
}

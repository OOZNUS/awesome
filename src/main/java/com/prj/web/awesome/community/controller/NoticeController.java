package com.prj.web.awesome.community.controller;

import com.prj.web.awesome.community.dto.NoticeDTO;
import com.prj.web.awesome.community.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/community")
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model){

        List<NoticeDTO> noticeList = noticeService.noticeList();

        model.addAttribute("noticeList", noticeList);

        return "html/community/notice/communityNotice";
    }
}

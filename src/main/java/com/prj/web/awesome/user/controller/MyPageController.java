package com.prj.web.awesome.user.controller;

import com.prj.web.awesome.user.dto.AddrDTO;
import com.prj.web.awesome.user.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequestMapping(value = "/user/myPage")
@Controller
public class MyPageController {

    @Autowired
    MyPageService service;

//    @GetMapping("/myHome")
//    public String myHome(Model model){
//        return "html/user/myPage/userMyPageHome";
//    }

    @GetMapping("/addr")
    public String addr(Model model){ return "orderAddress"; }


    @GetMapping("/addrList")
    public String addrList(Model model, HttpServletRequest request) {


        List<AddrDTO> loginID = service.addrList((String) request.getSession().getAttribute("loginID"));

        model.addAttribute("addrList", loginID);


        return "html/user/myPage/userMyPageAddrList";
    } //ulist

    @GetMapping("/addrselect")
    public String addrSerlect() {
        return "/html/order/orderAddress";
    }

    //  배송지추가
    @GetMapping("/addrInsert")
    public String addrInsertForm(){
        return "/html/user/myPage/userMyPageAddrInsert";
    }

    @PostMapping("/addrInsert")
    public String insertAddrUpdate( AddrDTO dto, HttpServletRequest request){
        dto.setUser_id((String)request.getSession().getAttribute("loginID"));


        AddrDTO addrInfo = new AddrDTO();
        addrInfo.setAddr_seq(dto.getAddr_seq());
        addrInfo.setAddr_name(dto.getAddr_name());
        addrInfo.setAddr_reciver(dto.getAddr_reciver());
        addrInfo.setAddr_postcode(dto.getAddr_postcode());
        addrInfo.setAddr_addr(dto.getAddr_addr());
        addrInfo.setAddr_addr2(dto.getAddr_addr2());
        addrInfo.setAddr_phone(dto.getAddr_phone());
        addrInfo.setAddr_default(dto.getAddr_default());
        if( dto.getAddr_default() == 'Y'){
            service.insertAddrUpdate(dto);
        }

        addrInfo.setUser_id(dto.getUser_id());

        service.addrInsert(addrInfo);


        return "redirect:/user/myPage/addrList";
    }





    @ResponseBody
    @PostMapping("/addrDelete")
    public String addrDelete(@RequestBody List<Integer> items) {

        for (int itemId : items) {
            int itemIdInt = itemId;

            System.out.println(itemIdInt);

            if (items != null) {
                service.addrDelete(itemIdInt);
            }
        }

        return "redirect:/user/myPage/addrList";
    } //delete


    // ** addr Update **
    @GetMapping("/addrUpdate")
    public String addrUpdatef(Model mv , AddrDTO dto, HttpServletRequest request) throws IOException {
        dto.setUser_id((String)request.getSession().getAttribute("loginID"));

        dto = service.addrSelectOne(dto);
        mv.addAttribute("addrInfo", dto);

        System.out.println(dto);

        return "html/user/myPage/userMyPageUpdate";
    }
    @PostMapping("/addrUpdate")
    public ModelAndView addrUpdate(HttpServletRequest request, ModelAndView mv, AddrDTO dto) throws IOException{
        dto.setUser_id((String)request.getSession().getAttribute("loginID"));

        // 2) Service 실행
        // => 성공 -> 내정보 표시, memberDetail
        // => 실패 -> 친절하게 안내하고 재수정 유도, updateForm
        String uri="html/user/myPage/userMyPageAddrList" ;

        // => Update 성공/실패 모두 출력시 필요하므로
        List<AddrDTO> loginID = service.addrList((String) request.getSession().getAttribute("loginID"));
        mv.addObject("addrList", loginID);


        if( dto.getAddr_default() == 'Y'){
            System.out.println( service.insertAddrUpdate(dto));
        }
        if ( service.addrUpdate(dto)>0 ) {

            mv.addObject("message", "~~ 회원정보 수정 성공 ~~");

        } else {
            uri="html/user/myPage/userMyPageUpdate" ;
            mv.addObject("message", "~~ 회원정보 수정 실패, 다시 하세요 ~~");
        }

        // 3) View 처리
        mv.setViewName(uri);
        return mv;
    } //update

    @GetMapping("/coupon")
    public String coupon(Model model){ return "html/user/myPage/userMyPageCoupon"; }

    @GetMapping("/order")
    public String order(Model model){ return "html/user/myPage/userMyPageOrder"; }






}

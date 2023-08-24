package com.example.demo.controller;

import com.example.demo.repository.IHoaDonChiTietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    private IHoaDonChiTietRepo iHoaDonChiTietRepo;
//    @GetMapping("/theo-thang")
//    private String viewTop10TheoThang(Model model,@RequestParam(value = "selectedMonth",required = false) String selectedMonth) {
//        int monthValue;
//        Month month = null;
//        if (selectedMonth == null) {
//            LocalDateTime now = LocalDateTime.now();
//            selectedMonth = String.valueOf(now.getMonth());
//            month = Month.valueOf(selectedMonth.toUpperCase());
//
//        }
//        monthValue = month.getValue();
//        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoThang(monthValue);
//        model.addAttribute("listTop10", listTop10);
//        return "thong-ke/thang";
//
//    }
@GetMapping("/theo-thang")
private String viewTop10TheoThang(Model model,@RequestParam(value = "selectedMonth",required = false) String selectedMonthStr) throws ParseException {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (selectedMonthStr == null || selectedMonthStr.isEmpty()) {
            // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
            Date currentDate = new Date();
            selectedMonthStr = sdf.format(currentDate);
        }
        Date selectedMonth = sdf.parse(selectedMonthStr);
        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoThang2(selectedMonth);
        model.addAttribute("listTop10", listTop10);
        model.addAttribute("now", selectedMonthStr);
        return "thong-ke/thang";
    } catch (Exception exception) {
        exception.printStackTrace();
        return "thong-ke/thang";

    }
}
    @GetMapping("/theo-ngay")
    private String viewTop10TheoNgay(Model model,@RequestParam(value = "selectedDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date selectedDate){
        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoNgay2(selectedDate);
        model.addAttribute("listTop10", listTop10);
        model.addAttribute("selectedDate", LocalDate.now());

        return "thong-ke/ngay";

    }
    @GetMapping("/theo-khoang-ngay")
    private String viewTop10TheoKhoang(Model model
            ,@RequestParam(value = "selectedDateStart",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime selectedDateStart
            ,@RequestParam(value = "selectedDateEnd",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime selectedDateEnd){

        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoKhoangNgay(selectedDateStart,selectedDateEnd);
        model.addAttribute("listTop10", listTop10);
        return "thong-ke/khoang";

    }
    @GetMapping("/theo-nam")
    private String viewTop10TheoNam(Model model,@RequestParam(value = "selectedYear",required = false) String selectedYearStr) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            if (selectedYearStr == null || selectedYearStr.isEmpty()) {
                // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
                Date currentDate = new Date();
                selectedYearStr = sdf.format(currentDate);
            }
            Date selectedYear = sdf.parse(selectedYearStr);
            List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoNam(selectedYear);
            model.addAttribute("listTop10", listTop10);
            model.addAttribute("now", selectedYearStr);
            return "thong-ke/nam";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "thong-ke/nam";

        }
    }

}

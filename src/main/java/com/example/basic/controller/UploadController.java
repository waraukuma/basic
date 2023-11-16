package com.example.basic.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {

    @GetMapping("/upload1")
    public String uplaod() {
        return "upload1";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public String upload1Post(
            MultipartHttpServletRequest mRequest,
            @RequestParam String a) {
        Iterator<String> iter = mRequest.getFileNames();
        while (iter.hasNext()) {
            String inputName = iter.next(); // input 태그의 name 값
            List<MultipartFile> mFiles = mRequest.getFiles(inputName);
            for (MultipartFile mFile : mFiles) {
                System.out.println(mFile.getOriginalFilename());
            }
        }

        // MultipartFile b = mRequest.getFile("b");
        // MultipartFile c = mRequest.getFile("c");
        // String bname = b.getOriginalFilename();
        // try {
        // b.transferTo(new File("c:/springboot/" + bname));
        // InputStream in = new FileInputStream("c:/springboot/" + bname);
        // BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        // while (true) {
        // String data = reader.readLine();
        // if (data == null)
        // break;
        // System.out.println(data);
        // }
        // reader.close();
        // in.close();

        // } catch (IllegalStateException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // // try catch 보편적

        return "업로드 완료 ";
        // + a + bname;
    }

    @GetMapping("/upload2")
    public String upload2() {
        return "upload2";
    }

    @PostMapping("/upload2")
    @ResponseBody
    public String upload2(
            @RequestParam("file") MultipartFile mFile) {

        String result = "";
        String oName = mFile.getOriginalFilename();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String date = sf.format(now);
        System.out.println(date);

        // 업로드될 폴더가 없다면 폴더 생성
        File f = new File("c:/files/" + date);
        if (!f.exists()) {
            f.mkdirs();
        }

        // 내일.. 중복파일이 존재하는지 검사+ 날짜선택
        // a.jpg -> a.jpg48425987345
        // a48425987345.jpg
        File isFile = new File("c:/files/" + date + "/" + oName);
        if (isFile.exists()) {
            int 점의위치 = oName.lastIndexOf(".");
            String 파일명 = oName.substring(0, 점의위치);
            String 확장자 = oName.substring(점의위치);
            oName = 파일명 + "__" + System.currentTimeMillis() + 확장자;
        }

        try {
            // c:/files/2023-11-16/a.jpg 날짜선택
            mFile.transferTo(new File("c:/files/" + date + "/" + oName));

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result += oName + "<br>" + mFile.getSize();
        return result;
    }

}

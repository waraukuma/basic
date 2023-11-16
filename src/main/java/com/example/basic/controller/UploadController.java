package com.example.basic.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public String upload2(@RequestParam("file") MultipartFile mFile) {
        String result = "";
        String oName = mFile.getOriginalFilename();
        // 파일명의 중복되었을 때 이름변경// 중요 /// // // //
        // String 클래스의 indexOf(), substring()
        // 01234 index
        // a.txt 업로드
        // a 2 .txt
        // .의 위치
        int dotSpot = oName.indexOf(".");
        // a
        String fileName = oName.substring(0, dotSpot);
        // .txt
        String extension = oName.substring(dotSpot);
        // oName = fileName + "2" + extension;
        oName = fileName + " " + System.currentTimeMillis() + extension;

        try {
            mFile.transferTo(new File("c:/springboot/" + oName));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        result += oName + "<br>" + mFile.getSize();
        return result;
    }

    @GetMapping("/upload3")
    public String upload3() {
        return "upload3";
    }

    @PostMapping("/upload3")
    @ResponseBody
    public String upload3Post(@ModelAttribute FileInfo info) {
        String result = "";
        String oName = info.getFile().getOriginalFilename();
        result += oName + "<br>" + info.getFile().getSize();
        return result;
    }

}

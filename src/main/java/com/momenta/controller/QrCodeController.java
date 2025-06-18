package com.momenta.controller;

import com.momenta.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qr")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping(value = "/{code}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQr(@PathVariable String code) throws Exception {
        byte[] qr = qrCodeService.generateQrImage(code, 300, 300);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qr);
    }
}

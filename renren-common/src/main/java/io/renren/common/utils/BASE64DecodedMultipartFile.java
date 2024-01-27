package io.renren.common.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

/**
 * base64转MultipartFile对象
 */
public class BASE64DecodedMultipartFile implements MultipartFile {
    private final byte[] imgContent;
    private final String header;

    public BASE64DecodedMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        // TODO 注意坑 data:image/jpeg;base64,头文件只需要===>data:image/jpeg
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    /**
     * base64转MultipartFile
     * @param imgBase64
     * @return
     */
    public static MultipartFile base64ToMultipartFile (String imgBase64) {
        MultipartFile image = null;
        StringBuilder base64 = new StringBuilder("");
        if (imgBase64 != null && !"".equals(imgBase64)) {
            base64.append(imgBase64);
            String[] baseStrs = base64.toString().split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            try {
                b = decoder.decodeBuffer(baseStrs[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < b.length; ++j) {
                if (b[j] < 0) {
                    b[j] += 256;
                }
            }
            image = new BASE64DecodedMultipartFile(b, baseStrs[0]);
        }
        return image;
    }
}

package com.alevel.java.morsecodec.codec;

public interface TextCodec {

    String encode(CharSequence input);

    String decode(CharSequence input);

}

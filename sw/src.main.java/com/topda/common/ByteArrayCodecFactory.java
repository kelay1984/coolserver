package com.topda.common;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;


public class ByteArrayCodecFactory implements ProtocolCodecFactory {
	  
    private ByteArrayDecoder decoder;
    private ByteArrayEncoder encoder;
    
    public ByteArrayCodecFactory() {
    	encoder = new ByteArrayEncoder();
        decoder = new ByteArrayDecoder();
    }


    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return (ProtocolDecoder) decoder;
    }


    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

}
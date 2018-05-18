package com.sztvis.buscloud.netty.service.codec;

import com.sztvis.buscloud.util.HexStringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Decoder4LoggingOnly extends ByteToMessageDecoder {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Logger weblog = LoggerFactory.getLogger("weblog");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String hex = buf2Str(in);
        log.info("ip={},hex = {}", ctx.channel().remoteAddress(), hex);
        weblog.info("ip={},hex = {}", ctx.channel().remoteAddress(), hex);

        ByteBuf buf = Unpooled.buffer();
        while (in.isReadable()) {
            buf.writeByte(in.readByte());
        }
        out.add(buf);
    }

    private String buf2Str(ByteBuf in) {
        byte[] dst = new byte[in.readableBytes()];
        in.getBytes(0, dst);
        return HexStringUtils.toHexString(dst);
    }
}

package com.fangln.dd.init.netty;

import java.io.Serializable;

public class RequestInfo implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 3640943381518187975L;
        private String data;
        private byte type;


        public void setData(String data) {
            this.data = data;
        }

        public void setType(byte type) {
            this.type = type;
        }

        public String getData() {
            return data;
        }

        public byte getType() {
            return type;
        }

}

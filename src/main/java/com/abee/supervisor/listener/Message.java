package com.abee.supervisor.listener;

import lombok.Data;

@Data
public class Message {

    public enum Type {
        /**
         * To activate user account.
         */
        ACT,
        /**
         * To forbid user account.
         */
        FOR
    }

    public static final int SIZE = 14;

    private Type type;

    private String serialNumber;

    @Override
    public String toString() {
        return type + ";" + serialNumber;
    }

    public static Message toMessage(String s) {
        String[] ss = s.split(";");
        if (ss.length != 2) {
            return null;
        }

        Type type;
        try {
            type = Type.valueOf(ss[0]);
        } catch (Exception e) {
            return null;
        }


        Message message = new Message();
        message.setType(type);
        message.setSerialNumber(ss[1]);
        return message;
    }

    public byte[] toByteArray() {
        return this.toString().getBytes();
    }

    public static Message toMessage(byte[] bs) {
        return toMessage(new String(bs));
    }
}

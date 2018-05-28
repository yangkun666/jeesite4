package com.miaocup.common.contants;

/**
 * @author beihui.hejun
 * @version V1.0
 * @Description: 常量
 * @date 2018/4/7
 */
public class MiaocupContants {

    public static final String CM_RUN_STATUS_KEY = "udp:cm_run_status:";
    public static final String CM_COMMUNICATION_KEY = "udp:communication:";
    public static final String CM_IP_KEY = "udp:cm_ip:";
    public static final String COLON = ":";
    public static final String MQ_TOPIC_TAG = "*";
    public static final String CHECK_MACHINE_STATUS_LIST = "task:check_machine:list";
    public static final String CHECK_MACHINE_STATUS_LOCK = "lock:check_machine:";
    public static final long CHECK_MACHINE_STATUS_LOCK_TIME = 10;
    public static final String CM_CUPS_COUNT_KEY = "machine:cups_count:";
    public static final String CM_STARTUP_IDS_KEY = "machine:startup_ids";
    public static final String CM_MAKING_COFFEE_LOCK = "lock:making_coffee:";
    public static final long CM_MAKING_COFFEE_LOCK_TIME = 120;
    public static final long MACHINE_CONFIG_EXPIRE_TIME = 600;
    public static final String COUPON_SHARE_LOCK = "lock:coupon_lock:";
    public static final long COUPON_SHARE_LOCK_TIME = 60 * 60 * 24;

}

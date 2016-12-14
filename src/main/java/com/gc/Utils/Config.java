package com.gc.Utils;

public class Config {
    public static final Long STATE_SUCCESS = 0L;
    public static final Long STATE_FAIL_LOGIN = 1L;
    public static final Long STATE_FAIL_OTHER = 2L;

    public static final String TEAM_STATE_APPLY = "apply";
    public static final String TEAM_STATE_INTEAM = "in_team";
    public static final String TEAM_STATE_INVITE = "be_invited";
    public static final String TEAM_STATE_APPLYFAIL = "apply_fail";
    public static final String TEAM_STATE_INVITEFAIL= "refuse_invite";

    public static final String ROLE_STUDENT = "student";
    public static final String ROLE_ADMIN = "admin";

    public static final String CREDIT_STATUS_APPLY = "待审核";
    public static final String CREDIT_STATUS_PASS = "已通过";
    public static final String CREDIT_STATUS_REFUSE = "未通过";
}

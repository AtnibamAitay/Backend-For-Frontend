package space.atnibam.steamedu.enums;

/**
 * @ClassName: GenderEnum
 * @Description: 性别枚举类，用于映射和转换性别值与文字描述
 * @Author: AtnibamAitay
 * @CreateTime: 2024-03-05 14:39
 **/
public enum GenderEnum {
    /**
     * 女性枚举
     */
    FEMALE(0, "女"),

    /**
     * 男性枚举
     */
    MALE(1, "男");

    /**
     * 枚举成员变量：性别编码
     */
    private final int code;
    /**
     * 枚举成员变量：性别描述
     */
    private final String desc;

    /**
     * 构造方法，初始化枚举成员的code和desc属性
     *
     * @param code 性别编码
     * @param desc 性别描述
     */
    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据给定的性别编码获取对应的GenderEnum枚举实例
     *
     * @param code 性别编码
     * @return 对应的GenderEnum枚举实例
     * @throws IllegalArgumentException 当输入的code无效时抛出异常
     */
    public static GenderEnum getByCode(Integer code) {
        for (GenderEnum gender : values()) {
            if (gender.getCode() == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }

    /**
     * 获取当前枚举实例所代表的性别编码
     *
     * @return 性别编码
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取当前枚举实例所代表的性别描述
     *
     * @return 性别描述
     */
    public String getDesc() {
        return desc;
    }
}

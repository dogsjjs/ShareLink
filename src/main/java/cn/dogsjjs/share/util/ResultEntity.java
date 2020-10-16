package cn.dogsjjs.share.util;

/**
 * 统一整个项目中Ajax请求返回的结果
 * @param <T>
 */
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    //封装当前处理结果成功与否
    private String result;

    //处理失败时返回的错误消息
    private String message;

    //要返回的数据
    private T data;

    /**
     * 请求处理成功且不需要返回数据时使用的工具方法
     * @param <Type> 返回数据的泛型
     * @return
     */
    public static <Type> ResultEntity<Type> successWithoutData(){
        return new ResultEntity<Type>(SUCCESS,null,null);
    }

    /**
     * 请求处理成功且需要返回数据时使用的工具方法
     * @param data 需要返回的数据
     * @param <Type> 返回数据的泛型
     * @return
     */
    public static <Type> ResultEntity<Type> successWithData(Type data){
        return new ResultEntity<Type>(SUCCESS,null,data);
    }

    /**
     * 请求处理失败时使用的工具方法
     * @param message 失败的错误消息
     * @param <Type> 返回数据的泛型
     * @return
     */
    public static <Type> ResultEntity<Type> failed(String message){
        return new ResultEntity<Type>(FAILED,message,null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

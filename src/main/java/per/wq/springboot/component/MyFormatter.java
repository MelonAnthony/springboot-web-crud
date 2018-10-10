package per.wq.springboot.component;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @Auther: Anthony
 * @Date: 2018/10/10 19:59
 * @Description:
 */
public class MyFormatter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return null;
    }
}

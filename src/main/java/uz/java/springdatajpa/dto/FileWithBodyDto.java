package uz.java.springdatajpa.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 6:15 AM
 */
@Getter
@Setter
public class FileWithBodyDto extends FileDto{
    private byte[] data;
}

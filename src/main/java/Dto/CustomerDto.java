package Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerDto {
    private String id;
    private String name;
    private String address;
    private double salary;
}

package com.example.productsshop.domain.dtos.user.wrappers;

import com.example.productsshop.domain.dtos.user.UsersWithSoldProductsXMLDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWrapperXMLDTO {

    @XmlElement(name = "user")
    private List<UsersWithSoldProductsXMLDTO> users;
}

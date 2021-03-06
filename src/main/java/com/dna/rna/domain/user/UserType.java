package com.dna.rna.domain.user;

/**
 * 유저의 role을 구분하기 위한 enumeration
 *
 * UserType.java
 * created 2020.4.1
 * @author Hyounjun kim <4whomtbts@gmail.com>
 *
 */

public enum UserType {

    // 경고 : 새로운 ROLE 추가시 절대 삽입 하지말고
    //        추가가 필요할 시에 숫자를 늘려서 뒤로 append 할 것
    USER,
    ADMIN

}

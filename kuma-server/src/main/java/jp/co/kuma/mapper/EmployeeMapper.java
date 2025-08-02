package jp.co.kuma.mapper;

import jp.co.kuma.annotation.AutoFill;
import jp.co.kuma.entity.Employee;
import jp.co.kuma.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * ユーザー名に基づいて社員を検索する
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    
    /**
     * 社員を新規作成する
     * @param employee
     * @return
     */
    @Insert("insert into employee(name,username,password,phone,status,create_time,update_time,create_user,update_user)" +
            "values(#{name},#{username},#{password},#{phone},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void create(Employee employee);

}

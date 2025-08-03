package jp.co.kuma.mapper;

import jp.co.kuma.annotation.AutoFill;
import jp.co.kuma.entity.Employee;
import jp.co.kuma.enumeration.OperationType;
import jp.co.kuma.vo.EmployeePageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    
    /**
     * ユーザー名に基づいて社員を検索する
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    
    /**
     * 社員を新規作成する
     *
     * @param employee
     * @return
     */
    @Insert("insert into employee(name,username,password,phone,status,create_time,update_time,create_user,update_user)" +
            "values(#{name},#{username},#{password},#{phone},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void create(Employee employee);
    
    /**
     * 社員の総数を取得
     *
     * @param name 社員名
     * @return count
     */
    int countByName(String name);
    
    /**
     * 社員のページングリストを取得
     *
     * @param offset   オフセット
     * @param pageSize ページサイズ
     * @param name     社員名
     * @return 社員ページリスト
     */
    List<EmployeePageVO> list(@Param("offset") int offset,
                              @Param("pageSize") int pageSize,
                              @Param("name") String name);
    
    /**
     * 社員の更新 自動的に更新ユーザーと更新時間を設定する
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);
    
    /**
     * 社員の詳細を取得
     *
     * @param id 社員ID
     * @return 社員情報
     */
    @Select("select * from employee where id = #{id}")
    Employee get(Long id);
}


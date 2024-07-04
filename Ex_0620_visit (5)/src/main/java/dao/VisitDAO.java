package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lombok.RequiredArgsConstructor;
import vo.VisitVO;

@RequiredArgsConstructor
public class VisitDAO {

	final SqlSession sqlSession;
	
	//방명록 전체 조회
	public List<VisitVO> selectList(){
		return sqlSession.selectList("v.visit_list");
	}
	
	public int insert(VisitVO vo) {
		return sqlSession.insert("v.visit_insert",vo);
	}
	
	public int delete(HashMap<String, Object> map) {
		return sqlSession.delete("v.visit_delete",map);
	}
	
	public VisitVO selectOne(int idx) {
		return sqlSession.selectOne("v.visit_select_one",idx);
	}
	
	public int update(VisitVO vo) {
		return sqlSession.update("v.visit_update",vo);
	}
}

















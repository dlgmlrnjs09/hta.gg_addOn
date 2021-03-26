package gg.hta.lol.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gg.hta.lol.vo.CommunityVo;

@Repository
public class CommunityDao {
	@Autowired private SqlSession sqlSession;
	private static String NAMESPACE="gg.hta.lol.mapper.CommunityMapper";
	public int insert(CommunityVo vo) {
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	public int count(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE + ".count", map);
	}
	public List<CommunityVo> list(HashMap<String, Object> map){
		return sqlSession.selectList(NAMESPACE+".list",map);
	}
	
	public List<CommunityVo> wholeList(HashMap<String, Object> map){
	    return sqlSession.selectList(NAMESPACE+".wholeList",map);
	}
	public int delete(int pNum) {
		return sqlSession.delete(NAMESPACE+".delete",pNum);
	}
	public int update(CommunityVo vo) {
		return sqlSession.update(NAMESPACE+".update",vo);
	}
	public CommunityVo select(int pNum) {
		return sqlSession.selectOne(NAMESPACE +".select",pNum);
	}
	public CommunityVo next(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE +".next",map);
	}
	public CommunityVo prev(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE +".prev",map);
	}
	public int addCount(int pNum) {
		return sqlSession.update(NAMESPACE+".addCount",pNum);
	}
	public int hit(int pNum) {
		return sqlSession.update(NAMESPACE+".hit",pNum);
	}
	public int hitCount(int pNum) {
		return sqlSession.selectOne(NAMESPACE+".hitCount",pNum);
	}
	public int noHit(int pNum) {
		return sqlSession.update(NAMESPACE+".noHit",pNum);
	}
	public int nohitCount(int pNum) {
		return sqlSession.selectOne(NAMESPACE+".nohitCount",pNum);
	}
	public List<CommunityVo> boardList(HashMap<String, Object> map){
		return sqlSession.selectList(NAMESPACE + ".boardList", map);
	}
	public int boardCount(String username) {
		return sqlSession.selectOne(NAMESPACE + ".boardCount", username);
	}
	
	public HashMap<String, Integer> getCurrentPnum() {
		return sqlSession.selectOne(NAMESPACE + ".getCurrentPnum");
	}
	
	public int getNextPnum() {
		return sqlSession.selectOne(NAMESPACE + ".getNextPnum");
	}
}
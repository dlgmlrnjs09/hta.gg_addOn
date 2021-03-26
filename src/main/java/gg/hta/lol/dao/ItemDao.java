package gg.hta.lol.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gg.hta.lol.vo.ItemVo;

@Repository
public class ItemDao {
	@Autowired private SqlSession sqlSession;
	public final String NAMESPACE="gg.hta.lol.mapper.ItemMapper";
	public int item_insert(ItemVo vo) {
		return sqlSession.insert(NAMESPACE+".item_insert",vo);
	}
	public ItemVo item_list(int inum) {
		return sqlSession.selectOne(NAMESPACE+".item_select",inum);
	}
	public List<ItemVo> item_alllist(){
		System.out.println("/");
		return sqlSession.selectList(NAMESPACE+".item_alllist");
	}

	public List<HashMap<String, Object>> sortPopItemByAll () {
		return sqlSession.selectList(NAMESPACE + ".sortPopItemByAll");
	}

	public List<HashMap<String, Object>> sortPopItemByPrice (HashMap<String, Integer> map) {
		return sqlSession.selectList(NAMESPACE + ".sortPopItemByPrice", map);
	}

	public List<HashMap<String, Object>> sortPopItemByTag (String tag) {
		return sqlSession.selectList(NAMESPACE + ".sortPopItemByTag", tag);
	}

	public List<HashMap<String, Integer>> getAllItemPrice(HashMap<String, Object> map) {
	    return sqlSession.selectList(NAMESPACE + ".getAllItemPrice", map);
    }

    public int getHaveItemWinloseCount(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE + ".getHaveItemWinloseCount", map);
	}

	public HashMap<String, Double> getKdaAvg(HashMap<String, String> map) {
		return sqlSession.selectOne(NAMESPACE + ".getKdaAvg", map);
	}
}

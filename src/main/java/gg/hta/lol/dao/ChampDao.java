package gg.hta.lol.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gg.hta.lol.joinvo.WinloseRuntimeVo;
import gg.hta.lol.joinvo.WinningSettingVo;
import gg.hta.lol.joinvo.championidTogetherVo;
import gg.hta.lol.vo.OnlyItemVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import gg.hta.lol.vo.ChampionVo;

@Repository
public class ChampDao {
	@Autowired
	SqlSession sqlSession;
	private final String NAMESPACE = "gg.hta.lol.mapper.ChampMapper";

	public int insertChamp(ChampionVo vo) {
		return sqlSession.insert(NAMESPACE + ".insertChamp", vo);
	}

	public int deleteChamp() {
		return sqlSession.delete(NAMESPACE + ".deleteChamp");
	}

	public List<ChampionVo> ChampAll_List() {
		return sqlSession.selectList(NAMESPACE + ".ChampAll_List");
	}

	public ChampionVo selectList(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".selectList", championid);
	}

	public int matchCount(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".matchCount", championid);
	}

	public int banCount(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".banCount", championid);
	}

	public int wincount(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".wincount", championid);
	}

	public Map<String, Object> allCount(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".allCount", championid);
	}

	public Map<String, Object> maxCount(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".maxCount", championid);
	}

	public Map<String, Object> banrank(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".banrank", championid);
	}

	public Map<String, Object> winrank(int championid) {
		return sqlSession.selectOne(NAMESPACE + ".winrank", championid);
	}
	public List<HashMap<String, Object>>  itemtree(int championid){
		return sqlSession.selectList(NAMESPACE+".itemtree",championid);
	}
	public List<HashMap<String, Object>>  banlistAll( ){
		return sqlSession.selectList(NAMESPACE+".banlistAll");
	}
	public List<HashMap<String, Object>>  winlistAll( ){
		return sqlSession.selectList(NAMESPACE+".winlistAll");
	}
	public List<HashMap<String, Object>>  pickrank(){
		return sqlSession.selectList(NAMESPACE+".pickrank");
	}

	public List<championidTogetherVo> getChampionidTogether(int championid) {
		return sqlSession.selectList(NAMESPACE + ".getChampionidTogether", championid);
	}

	public List<WinningSettingVo> getWinningSetting (int championid) {
		return sqlSession.selectList(NAMESPACE + ".getWinningSetting", championid);
	}

	public List<WinloseRuntimeVo> getWinloseRuntime (int championid) {
		return sqlSession.selectList(NAMESPACE + ".getWinloseRuntime", championid);
	}

	public List<HashMap<String, Object>> getChampionRank (int championid) {
		return sqlSession.selectList(NAMESPACE + ".getChampionRank", championid);
	}

	public List<OnlyItemVo> getChampionRankerItemList (HashMap<String, Object> map) {
		return sqlSession.selectList(NAMESPACE + ".getChampionRankerItemList", map);
	}

	public int getWinloseCountByTier (HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE + ".getWinloseCountByTier", map);
	}
}

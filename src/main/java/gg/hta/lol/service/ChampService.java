package gg.hta.lol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gg.hta.lol.joinvo.WinloseRuntimeVo;
import gg.hta.lol.joinvo.WinningSettingVo;
import gg.hta.lol.joinvo.championidTogetherVo;
import gg.hta.lol.vo.OnlyItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.ChampDao;
import gg.hta.lol.vo.ChampionVo;

@Service
public class ChampService {
	@Autowired  ChampDao dao;
	public int insertChamp(ChampionVo vo) {
		return dao.insertChamp(vo);
	}
	public int deleteChamp() {
		return dao.deleteChamp();
	}
	public List<ChampionVo> ChampAll_List(){
		return dao.ChampAll_List();
	}
	public ChampionVo selectList(int championid) {
		return dao.selectList(championid);
	}
	public int matchCount(int championid) {
		return dao.matchCount(championid);
	}
	public int banCount(int championid) {
		return dao.banCount(championid);
	}
	public int wincount(int championid) {
		return dao.wincount(championid);
	}public Map<String, Object> allCount(int championid){
		return dao.allCount(championid);
	}
	public Map<String,Object> maxCount(int championid){
		return dao.maxCount(championid);
	}
	public Map<String, Object> banrank(int championid){
		return dao.banrank(championid);
	}public Map<String,Object> winrank(int championid){
		return dao.winrank(championid);
	}
	public List<HashMap<String, Object>> itemtree(int championid){
		return dao.itemtree(championid);
	}
	public List<HashMap<String, Object>> winlistAll( ){
		return dao.winlistAll();
	}
	public List<HashMap<String, Object>> banlistAll(){
		return dao.banlistAll();
	}
	public List<HashMap<String, Object>> pickrank( ){
		return dao.pickrank();
	}
	public List<championidTogetherVo> getChampionidTogether(int championid) {
		return dao.getChampionidTogether(championid);
	}

	public List<WinningSettingVo> getWinningSetting (int championid) {
		return dao.getWinningSetting(championid);
	}

	public List<WinloseRuntimeVo> getWinloseRuntime (int championid) {
		return dao.getWinloseRuntime(championid);
	}

	public List<HashMap<String, Object>> getChampionRank (int championid) {
		return dao.getChampionRank(championid);
	}

	public List<OnlyItemVo> getChampionRankerItemList(HashMap<String, Object> map) {
		return dao.getChampionRankerItemList(map);
	}

	public int getWinloseCountByTier (HashMap<String, Object> map) {
		return dao.getWinloseCountByTier(map);
	}
}

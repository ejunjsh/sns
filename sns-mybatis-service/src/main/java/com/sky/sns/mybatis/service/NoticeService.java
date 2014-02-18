package com.sky.sns.mybatis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sky.sns.mybatis.entity.Notice;
import com.sky.sns.mybatis.iService.INoticeService;
import com.sky.sns.mybatis.mapper.NoticeMapper;



public class NoticeService implements INoticeService {
	
	 private NoticeMapper noticeMapper;

	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Transactional
	public void insertNotice(Notice notice) {
		noticeMapper.insertNotice(notice);
	}

	@Transactional
	public void updateNoticeToRead(long id) {
		noticeMapper.updateNoticeToRead(id);
	}

	@Transactional(readOnly=true)
	public List<Notice> getNoticeByUserId(long id, int pageStart, int pageSize) {
		return noticeMapper.getNoticeByUserId(id, pageStart, pageSize);
	}

	

	@Transactional(readOnly=true)
	public long countNoticeByUserId(long id) {
		return noticeMapper.countNoticeByUserId(id);
	}

	
	@Transactional(readOnly=true)
	public List<Notice> getSysNotice(com.sky.sns.mybatis.searchEntity.Notice notice) {
		notice.setTotal(noticeMapper.countSysNotice(notice));
		return noticeMapper.getSysNotice(notice);
	}

	@Transactional(readOnly=true)
	public Notice getNoticeById(long id) {
		return noticeMapper.getNoticeById(id);
	}

	@Transactional(readOnly=true)
	public List<Notice> getUnreadNotice(long id) {
		return noticeMapper.getUnreadNotice(id);
	}
}

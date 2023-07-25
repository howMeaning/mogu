package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.CommentDTO;
import org.example.dto.Result;
import org.example.dto.TestDTO;
import org.example.dto.TransferDTO;
import org.example.entity.Comment;

public interface CommentService extends IService<Comment> {
    TransferDTO getList(TestDTO testDTO);

    Comment addComment(Comment comment);
}

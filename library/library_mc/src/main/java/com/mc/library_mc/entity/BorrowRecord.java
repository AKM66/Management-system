package com.mc.library_mc.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("borrow_record")
public class BorrowRecord {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private Long bookId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private LocalDateTime dueTime;
    private Integer status;  // 0:借阅中, 1:已归还, 2:逾期

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 借阅记录表
 * </p>
 *
 * @author admin
 * @since 2026-04-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class BorrowRecord implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 图书ID
     */
      private Long bookId;

      /**
     * 书名快照
     */
      private String bookTitle;

      /**
     * 借阅人
     */
      private String borrower;

      /**
     * 联系电话
     */
      private String phone;

      /**
     * 借阅日期
     */
      private LocalDate borrowDate;

      /**
     * 应还日期
     */
      private LocalDate dueDate;

      /**
     * 实际归还日期
     */
      private LocalDate returnDate;

      /**
     * 0-借出中 1-已归还
     */
      private Integer status;

      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;


}

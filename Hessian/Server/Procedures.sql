DELIMITER //

DROP PROCEDURE IF EXISTS GETAMOUNT//
CREATE PROCEDURE GETAMOUNT (OUT val LONG, IN idx INT)
  BEGIN
    SELECT `value` INTO val FROM `users` WHERE `user_id` = idx;
  END//

DROP PROCEDURE IF EXISTS ADDAMOUNT//
CREATE PROCEDURE ADDAMOUNT(IN idx INT,IN val LONG)
  BEGIN
    DECLARE V LONG;
    SELECT `value` INTO @V FROM `users` WHERE `user_id`=idx;
    IF EXISTS(SELECT `value` FROM `users` WHERE `user_id`=idx) THEN
      UPDATE `users` SET `value`=val + @V WHERE `user_id` = idx;
    ELSE
      INSERT INTO `users`(`user_id`,`value`) VALUES(idx,val);
    END IF;
  END //

DELIMITER ;

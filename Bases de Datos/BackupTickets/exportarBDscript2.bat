cd ../wamp64/bin/mysql/mysql5.7.11/bin
mysqldump --user=root --password=root  ticket > ../../../../../BackupTickets/bckpTickets/tickets.sql
cd ../../../../../"Program Files"/WinRAR/
rar a -agDD-MMM-YY "E:\BackupTicketsDB\BackupTickets_" C:\BackupTickets\bckpTickets\*.*
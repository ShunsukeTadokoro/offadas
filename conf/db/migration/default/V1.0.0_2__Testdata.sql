# insert test data
insert into user (id, email , password, created_at  ) values ( 1, 'test1@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 2, 'test2@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 3, 'test3@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 4, 'test4@hoge.com', 'test', current_date ) ;
insert into user (id, email , password, created_at  ) values ( 5, 'test5@hoge.com', 'test', current_date ) ;

insert into login ( id , user_id , logged_in ) values ( 1, 1, current_timestamp );
insert into login ( id , user_id , logged_in ) values ( 2, 2, current_timestamp );

insert into status ( code , name ) values ( 'FREE', 'フリープラン' );
insert into status ( code , name ) values ( 'PREM', 'プレミアムプラン' );
insert into status ( code , name ) values ( 'CAMP' , 'キャンペーンプラン' );

insert into offerset ( id , user_id ,name , status_code ) values (1, 1,'test1-1','FREE');
insert into offerset ( id , user_id ,name , status_code ) values (11, 1,'test1-2','FREE');
insert into offerset ( id , user_id ,name , status_code ) values (111, 1,'test1-3','PREM');
insert into offerset ( id , user_id ,name , status_code ) values (1111, 2,'test2-1','CAMP');

insert into offer ( id, offerset_id , target_class , content_class ) values ( 1, 1, 'test_target1-1', 'test_content1-1') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 2, 1, 'test_target1-2', 'test_content1-2') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 3, 11, 'test_target2-1', 'test_content2-1') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 4, 111, 'test_target3-1', 'test_content3-1') ;
insert into offer ( id, offerset_id , target_class , content_class ) values ( 5, 1111, 'test_target4-1', 'test_content4-1') ;

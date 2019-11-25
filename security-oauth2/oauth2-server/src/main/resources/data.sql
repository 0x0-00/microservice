INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('fooClientIdPassword', 'secret', 'foo,read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('SampleClientId', '{noop}secret', 'read,write,foo,bar',
	'implicit,password,authorization_code,refresh_token,client_credentials', 'http://www.baidu.com,https://www.getpostman.com/oauth2/callback,http://www.myssoclient.com:8989/login', null, 36000, 36000, null, false);
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('barClientIdPassword', 'secret', 'bar,read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);

INSERT INTO oauth_client_details("client_id", "resource_ids", "client_secret", "scope", "authorized_grant_types", "web_server_redirect_uri", "authorities", "access_token_validity", "refresh_token_validity", "additional_information", "autoapprove")
VALUES
('bimplatform', NULL, '{noop}secret', 'bar,read,write', 'password,authorization_code,refresh_token', 'http://b.dafengbim.com:8080/login/callback,http://b.dafengbim.com/login/callback,http://www.dafengbim.com:8080/login/callback,http://www.dafengbim.com/login/callback,', NULL, NULL, NULL, NULL, true);

INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('bbsAppId', '{noop}secret', 'bar,read,write',
	'password,authorization_code,refresh_token', 'http://bbs.jufengbim.com:8080/login/callback,http://bbs.jufengbim.com:8080/login', null, 36000, 36000, null, true);
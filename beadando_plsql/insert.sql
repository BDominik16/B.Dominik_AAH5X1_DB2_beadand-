Create or replace procedure InsertAuto (szin in char,evjarat in number,tipus in char,rendszam in char, id in number) is 

begin 
insert into AUTO(SZ�N,�VJ�RAT,T�PUS,RENDSZ�M,ID) values(szin,evjarat,tipus,rendszam,id); 
 

end;
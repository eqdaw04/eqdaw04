SET SERVEROUTPUT ON
/*
CREATE OR REPLACE PACKAGE Pkg_Clasificacion IS
  TYPE Nes_punt IS TABLE OF INTEGER;
  TYPE Nes_nom_equ IS TABLE OF Equipo.nombre%type;
  FUNCTION Calcular_puntuacion(P_idequipo INTEGER)
    RETURN NUMBER;
  PROCEDURE Clasif (V_nes_punt OUT Nes_punt, V_nom_equ OUT Nes_nom_equ);
END Pkg_Clasificacion;
/

CREATE OR REPLACE PACKAGE BODY Pkg_Clasificacion IS
  FUNCTION Calcular_puntuacion (P_idequipo INTEGER)
  RETURN NUMBER
  AS
  CURSOR C_puntuacion IS
    SELECT NVL(Puntuacion,0) FROM Marcador 
    WHERE Id_equipo = P_idequipo;
  V_puntot Marcador.Puntuacion%TYPE:= 0;
  V_puntuacion Marcador.Puntuacion%TYPE;
  BEGIN
    OPEN C_puntuacion;  
    LOOP
      FETCH C_puntuacion INTO V_puntuacion;
      EXIT WHEN C_puntuacion%NOTFOUND;
      V_puntot := V_puntot+V_puntuacion;
    END LOOP;
    CLOSE C_puntuacion;
  RETURN V_puntot;
  END;
  ---
 PROCEDURE Clasif (V_nes_punt OUT Nes_punt, V_nom_equ OUT Nes_nom_equ)
  AS
    CURSOR C_idequipos IS
      SELECT DISTINCT Id_equipo FROM MARCADOR; 
    V_idequipo MARCADOR.ID_EQUIPO%TYPE;
    V_x INTEGER:=1;
    V_y INTEGER:=1;
  BEGIN
    V_nes_punt := Nes_punt();
    V_nom_equ := Nes_nom_equ();
    OPEN C_idequipos;
    LOOP
      FETCH C_idequipos INTO V_idequipo;
      EXIT WHEN C_idequipos%NOTFOUND;
        V_nes_punt.EXTEND;
          SELECT Calcular_puntuacion(V_idequipo) INTO V_nes_punt(V_x) FROM DUAL;
        V_nom_equ.EXTEND;
          SELECT Nombre INTO V_nom_equ(V_x) FROM Equipo WHERE Id_equipo = V_idequipo;
        V_x := V_x + 1;
    END LOOP;
IF V_nes_punt.COUNT > 0 THEN
    IF V_nom_equ.COUNT > 0 THEN
     FOR i IN V_nes_punt.FIRST..V_nes_punt.LAST LOOP
        DBMS_OUTPUT.PUT_LINE(V_nom_equ(i)||' '||V_nes_punt(i));   
        END LOOP;
      END IF;
    END IF;
END;
END Pkg_Clasificacion;
 / 
--Procedimiento an�nimo de llamada
DECLARE
      V_tab  Pkg_Clasificacion.Nes_punt;
      V_tabl  Pkg_Clasificacion.Nes_nom_equ;
      V_nombre Equipo.Nombre%TYPE;
      V_Puntuacion Marcador.Puntuacion%TYPE;
BEGIN
    Pkg_Clasificacion.Clasif(V_tab,V_tabl);
        FOR i IN V_tab.FIRST..V_tab.LAST LOOP
        DBMS_OUTPUT.PUT_LINE(V_tab(i)||' '||V_tabl(i));   
        END LOOP;
END;
*/

DROP PACKAGE Pkg_Clasificacion;
CREATE OR REPLACE PACKAGE Pkg_Clasificacion IS
      TYPE TCURSOR IS REF CURSOR;
      PROCEDURE clasif (C_partidos OUT TCURSOR);
END Pkg_Clasificacion;
/
CREATE OR REPLACE PACKAGE BODY Pkg_Clasificacion IS
  PROCEDURE clasif (C_partidos OUT TCURSOR) AS
  BEGIN
  OPEN C_partidos for
      SELECT E.Nombre AS Equipo, SUM(M.Puntuacion) AS punto FROM EQUIPO E,MARCADOR M 
            WHERE E.ID_EQUIPO = M.ID_EQUIPO
            GROUP BY  E.Nombre
            ORDER BY punto DESC;
  END;
END Pkg_Clasificacion;

--Procedimiento an�nimo de llamada
DECLARE
      V_Cur  Pkg_Clasificacion.TCURSOR;
      V_nombre Equipo.Nombre%TYPE;
      V_Puntuacion Marcador.Puntuacion%TYPE;
BEGIN
    Pkg_Clasificacion.clasif(V_Cur);
    LOOP
        FETCH V_Cur INTO V_nombre,V_Puntuacion;
        EXIT WHEN V_CUR%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(V_nombre || ' ' ||  V_Puntuacion);
    END LOOP;
END;



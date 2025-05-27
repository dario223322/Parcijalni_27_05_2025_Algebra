-- Procedure

-- Za Polaznika
CREATE PROCEDURE UnesiPolaznika
	@Ime NVARCHAR(100),
	@Prezime NVARCHAR(100)
AS
BEGIN
	INSERT INTO Polaznik (Ime, Prezime) VALUES (@Ime, @Prezime)
END

-- Za program obrazovanja
CREATE PROCEDURE UnesiProgram
	@Naziv NVARCHAR(100),
	@CSVET INT
AS
BEGIN
	INSERT INTO ProgramObrazovanja (Naziv, CSVET) VALUES (@Naziv, @CSVET)
END

-- Za polaznika
CREATE PROCEDURE UpisiPolaznika
    @IDPolaznik INT,
    @IDProgram INT
AS
BEGIN
    INSERT INTO Upis (IDPolaznik, IDProgramObrazovanja) VALUES (@IDPolaznik, @IDProgram)
END

-- Prebacivanje polaznika u drugi program
CREATE PROCEDURE PrebaciPolaznika
    @IDPolaznik INT,
    @IDProgramNovi INT
AS
BEGIN
    DELETE FROM Upis WHERE IDPolaznik = @IDPolaznik;
    INSERT INTO Upis (IDPolaznik, IDProgramObrazovanja) VALUES (@IDPolaznik, @IDProgramNovi);
END

-- Ispis polaznika
CREATE PROCEDURE IspisiPolaznikeZaProgram
    @IDProgram INT
AS
BEGIN
    SELECT p.Ime, p.Prezime, pr.Naziv, pr.CSVET
    FROM Upis u
    JOIN Polaznik p ON u.IDPolaznik = p.PolaznikID
    JOIN ProgramObrazovanja pr ON u.IDProgramObrazovanja = pr.ProgramObrazovanjaID
    WHERE pr.ProgramObrazovanjaID = @IDProgram
END
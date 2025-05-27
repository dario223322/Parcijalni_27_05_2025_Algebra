CREATE DATABASE JavaAdv;
GO

USE JavaAdv;
GO

-- Polaznik tablica
CREATE TABLE Polaznik (
	PolaznikID INT IDENTITY(1,1) PRIMARY KEY,
	Ime NVARCHAR(100) NOT NULL,
	Prezime NVARCHAR(100) NOT NULL
);

-- ProgramObrazovanja tablica
CREATE TABLE ProgramObrazovanja (
	ProgramObrazovanjaID INT IDENTITY(1,1) PRIMARY KEY,
	Naziv NVARCHAR(100) NOT NULL,
	CSVET INT NOT NULL
);

-- Upis tablica
CREATE TABLE Upis (
	UpisID INT IDENTITY(1,1) PRIMARY KEY,
	IDProgramObrazovanja INT NOT NULL,
	IDPolaznik INT NULL,
	FOREIGN KEY (IDProgramObrazovanja) REFERENCES ProgramObrazovanja(ProgramObrazovanjaID),
	FOREIGN KEY (IDPolaznik) REFERENCES Polaznik(PolaznikID)
);
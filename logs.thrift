struct Log{
  1: i16 v,
  2: string ip,
  3: string message,
  4: string date,
  5: i16 id
}

service CreateLogFile {
	void create()
}

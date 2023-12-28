#!/bin/bash

kafka_container_name=$1
samples_path="./samples"
compact_path="$samples_path"/compact

if [ ! -d "$compact_path" ]; then
    mkdir -p "$compact_path"
    echo "Created directory: $compact_path"
else
    rm "$compact_path"/*
fi

for file in "$samples_path"/*; do
	if [ -f "$file" ]; then
		echo "Processing file: $file"
		
		file_name="$(basename -- "$file")"
		file_name_without_extension="${file_name%.*}"
		compact_file_path="$samples_path"/compact/"$file_name_without_extension-compact.json"
		
		jq -c . $file > "$compact_file_path" && sed -i "1s/^/$RANDOM\&/" "$compact_file_path"

		echo "File created $compact_file_path"
	fi
done

docker exec "$kafka_container_name" rm -r /tmp/compact/ 2> /dev/null

for file_compact in "$compact_path"/*; do
  docker exec -i "$kafka_container_name" kafka-console-producer --topic live-results --broker-list localhost:9092 --property parse.key=true --property key.separator="&" < "$file_compact"
done

rm -r $compact_path

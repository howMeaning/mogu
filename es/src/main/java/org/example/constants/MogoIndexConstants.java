package org.example.constants;

/**
 * @descrition:
 * @author:how meaningful
 * @date:2023/7/25
 **/

public class MogoIndexConstants {
    public static final String MAPPING_TEMPLATE = "PUT /mogu \n" +
            "{\n" +
            "  \"settings\": {\n" +
            "    \"analysis\": {\n" +
            "      \"analyzer\":{\n" +
            "        \"text_anlyzer\": {\n" +
            "          \"tokenizer\": \"ik_max_word\",\n" +
            "          \"filter\": \"py\"\n" +
            "      },\n" +
            "      \"completion_analyzer\": {\n" +
            "        \"tokenizer\": \"keyword\",\n" +
            "        \"filter\": \"py\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"filter\":{\n" +
            "       \"py\": {\n" +
            "          \"type\": \"pinyin\",\n" +
            "          \"keep_full_pinyin\": false,\n" +
            "          \"keep_joined_full_pinyin\": true,\n" +
            "          \"keep_original\": true,\n" +
            "          \"limit_first_letter_length\": 16,\n" +
            "          \"remove_duplicated_term\": true,\n" +
            "          \"none_chinese_pinyin_tokenize\": false\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"mappings\":{\n" +
            "    \"properties\":{\n" +
            "      \"uid\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"title\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"text_anlyzer\",\n" +
            "        \"search_analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"content\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"text_anlyzer\",\n" +
            "        \"search_analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"tag_uid\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"click_count\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"collect_count\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"file_uid\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"status\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"create_time\":{\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"update_time\":{\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"admin_uid\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"author\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"text_anlyzer\",\n" +
            "        \"search_analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"articles_part\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"blog_sort_uid\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"level\":{\n" +
            "        \"type\":\"short\"\n" +
            "      },\n" +
            "      \"is_publish\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"sort\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"open_comment\":{\n" +
            "        \"type\":\"short\"\n" +
            "      },\n" +
            "      \"type\":{\n" +
            "        \"type\":\"short\"\n" +
            "      },\n" +
            "      \"oid\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"user_uid\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \n" +
            "      \"article_source\":{\n" +
            "        \"type\":\"short\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}

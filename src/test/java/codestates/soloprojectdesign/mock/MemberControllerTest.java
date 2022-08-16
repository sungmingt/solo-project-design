package codestates.soloprojectdesign.mock;

import codestates.soloprojectdesign.controller.MemberController;
import codestates.soloprojectdesign.domain.CompanyType;
import codestates.soloprojectdesign.domain.Member;
import codestates.soloprojectdesign.domain.dto.RequestDto;
import codestates.soloprojectdesign.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;
    @Autowired
    private Gson gson;

    @DisplayName("전체 회원 조회")
    @Test
    public void findAllTest() throws Exception {

        //given
        List<Member> members = List.of(
                new Member(1L, "member1", "male", "자바금융", "금융업", "서울"),
                new Member(2L, "member2", "female", "자바건설", "건설업", "강원"),
                new Member(3L, "member3", "male", "자바서비스", "서비스업", "경기"));

        int page = 1;
        int size = 3;
        Page<Member> memberPage = new PageImpl<>(
                members, PageRequest.of(page-1, size, Sort.by("id").descending()), members.size());

        given(memberService.findAll(Mockito.anyInt(), Mockito.anyInt())).willReturn(memberPage);


        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/members")
                                .param("page", String.valueOf(page))
                                .param("size", String.valueOf(size))
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andDo(document("find-all",
                        requestParameters(
                                parameterWithName("page").description("page index"),
                                parameterWithName("size").description("page size")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("응답 데이터"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].company_name").type(JsonFieldType.STRING).description("사업체 이름"),
                                        fieldWithPath("data[].company_type").type(JsonFieldType.STRING).description("사업체 업종"),
                                        fieldWithPath("data[].company_location").type(JsonFieldType.STRING).description("사업체 지역"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("page information"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("page index"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("page size"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("total elements"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("total pages")
                                )
                        )
                ));
    }

    @DisplayName("특정 조건에 맞게 조회")
    @Test
    public void findFilteredTest() throws Exception {

        //given
        RequestDto requestDto = new RequestDto("name", "건설업");
        String content = gson.toJson(requestDto);

        List<Member> members = List.of(
                new Member(1L, "member1", "male", "스프링건설", "건설업", "서울"),
                new Member(2L, "member2", "female", "자바건설", "건설업", "강원"),
                new Member(3L, "member3", "male", "노드건설", "건설업", "경기"));

        int page = 1;
        int size = 3;
        Page<Member> memberPage = new PageImpl<>(
                members, PageRequest.of(page-1, size, Sort.by("id").descending()), size);

        given(memberService.findFiltered(Mockito.any(RequestDto.class), Mockito.anyInt(), Mockito.anyInt())).willReturn(memberPage);


        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/members")
                                .param("page", String.valueOf(page))
                                .param("size", String.valueOf(size))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andDo(document("find-filtered",
                        requestParameters(
                                parameterWithName("page").description("page index"),
                                parameterWithName("size").description("page size")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("응답 데이터"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].company_name").type(JsonFieldType.STRING).description("사업체 이름"),
                                        fieldWithPath("data[].company_type").type(JsonFieldType.STRING).description("사업체 업종"),
                                        fieldWithPath("data[].company_location").type(JsonFieldType.STRING).description("사업체 지역"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("page information"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("page index"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("page size"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("total elements"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("total pages")
                                )
                        )
                ));
    }

}

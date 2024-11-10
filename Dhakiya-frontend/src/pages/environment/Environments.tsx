import { SidebarLayout } from "../../components/layouts/sidebarlayout/SidebarLayout";
import { Button } from "../../components/button/Button";
import { LabelBar } from "../../components/labelbar/LabelBar";
import { OverviewList } from "../../components/overviewlist/OverviewList";
import { useFetch } from "../../hooks/useApi";
import { useEffect } from "react";

export const Environments = () => {
    const { data: environments, error } = useFetch("environments");

    const addEnvironment = () => {
        console.log("add");
        // TODO make function to create new environment
    };

    useEffect(() => {
        // temp useEffect. delete me later!
        console.log(environments);
    }, [environments]);

    if (error != null) {
        return <div>error</div>;
    }

    return (
        <div>
            <LabelBar label={"Environments"} />
            <SidebarLayout
                leftContent={
                    <>
                        {environments && (
                            <OverviewList
                                list={environments}
                                linkTo={"environment"}
                            />
                        )}
                    </>
                }
                rightContent={
                    <div>
                        <Button content="add +" handleClick={addEnvironment} />
                    </div>
                }
            />
        </div>
    );
};
